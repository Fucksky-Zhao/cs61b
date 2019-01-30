package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Iterator;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }

    @Test
    public void forTest() {
        ArrayRingBuffer<String> fuck = new ArrayRingBuffer<>(10);
        fuck.enqueue("MY");
        fuck.enqueue("NAME");
        fuck.enqueue("IS");
        fuck.enqueue("FUCKSKY");
        for (String s : fuck) {
            System.out.println(s);
        }
    }
} 
