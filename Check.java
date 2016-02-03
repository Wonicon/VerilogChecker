import java.io.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Check {
    public static void main(String[] args) throws Exception {
        InputStream file = new FileInputStream(args[0]);
        ANTLRInputStream input = new ANTLRInputStream(file);
        Verilog2001Lexer lexer = new Verilog2001Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        Verilog2001Parser parser = new Verilog2001Parser(tokens);
        ParseTree tree = parser.source_text();

        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new CheckIdentifier(), tree);
    }
}

