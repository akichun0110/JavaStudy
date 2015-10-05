import java.net.*;
import java.io.*;

public interface ItemQuoteDecoder {
    ItemQuote decode(InputStream source) throws IOException;
    ItemQuote decode(DatagramPacket packet) throws IOException;
}