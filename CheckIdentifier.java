import java.util.*;

public class CheckIdentifier extends Verilog2001BaseListener {
    private boolean register = false;
    private boolean connect = false;
    private boolean port = false;
    private boolean ignore = false;

    private Set<String> table = new HashSet<String>();

    @Override public void enterModule_identifier(Verilog2001Parser.Module_identifierContext ctx) {
        ignore = true;
    }

    @Override public void exitModule_identifier(Verilog2001Parser.Module_identifierContext ctx) {
        ignore = false;
    }

    @Override public void enterParameter_declaration(Verilog2001Parser.Parameter_declarationContext ctx) {
        register = true;
    }

    @Override public void exitParameter_declaration(Verilog2001Parser.Parameter_declarationContext ctx) {
        register = false;
    }

    @Override public void enterEvent_declaration(Verilog2001Parser.Event_declarationContext ctx) {
        register = true;
    }

    @Override public void exitEvent_declaration(Verilog2001Parser.Event_declarationContext ctx) {
        register = false;
    }

    @Override public void enterNet_declaration(Verilog2001Parser.Net_declarationContext ctx) {
        register = true;
    }

    @Override public void exitNet_declaration(Verilog2001Parser.Net_declarationContext ctx) {
        register = false;
    }

    @Override public void enterReg_declaration(Verilog2001Parser.Reg_declarationContext ctx) {
        register = true;
    }

    @Override public void exitReg_declaration(Verilog2001Parser.Reg_declarationContext ctx) {
        register = false;
    }

    @Override public void enterList_of_port_declarations(Verilog2001Parser.List_of_port_declarationsContext ctx) {
        register = true;
    }

    @Override public void exitList_of_port_declarations(Verilog2001Parser.List_of_port_declarationsContext ctx) {
        register = false;
    }

    @Override public void enterPort_identifier(Verilog2001Parser.Port_identifierContext ctx) {
        port = true;
    }

    @Override public void exitPort_identifier(Verilog2001Parser.Port_identifierContext ctx) {
        port = false;
    }

    @Override public void enterList_of_port_connections(Verilog2001Parser.List_of_port_connectionsContext ctx) {
        connect = true;
    }

    @Override public void exitList_of_port_connections(Verilog2001Parser.List_of_port_connectionsContext ctx) {
        connect = false;
    }

    private void check(String sym, int line) {
        // The port names of instances are ignored
        if ((port && connect) || ignore) {
            return;
        }

        boolean is_contained = table.contains(sym);

        if (register) {
            if (is_contained) {
                System.out.printf("Line %d: he %s is already defined!\n", line, sym);
            } else {
                table.add(sym);
            }
        } else if (!is_contained) {
            System.out.printf("Line %d: %s\n", line, sym);
        } else {
            // Used after define
        }
    }

    @Override public void enterIdentifier(Verilog2001Parser.IdentifierContext ctx) {
        check(ctx.getText(), ctx.getStart().getLine());
    }

    @Override public void enterSimple_hierarchical_branch(Verilog2001Parser.Simple_hierarchical_branchContext ctx) {
        boolean old_register = register;
        register = false;
        check(ctx.getText(), ctx.getStart().getLine());
        register = old_register;
    }
}

