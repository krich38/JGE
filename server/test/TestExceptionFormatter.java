import org.jge.protocol.serverstatus.ServerDiagnostics;
import org.jge.server.util.DiagnosticCollection;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class TestExceptionFormatter {
    @Test
    public void testExceptions() {
        DiagnosticCollection.addException(new Exception("Test some exception"));

        System.out.println(DiagnosticCollection.getExceptions());
        Assert.assertTrue(true);
    }
}
