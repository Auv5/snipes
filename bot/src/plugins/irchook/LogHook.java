/*
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package plugins.irchook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.ossnipes.snipes.lib.events.Event;

public class LogHook extends Hook
{

    @Override
    public void line(Event e, String line)
    {
        this._file.println(e.toString() + ": " + line);
    }

    @Override
    public boolean init()
    {
        try
        {
            this._file = new PrintStream(new FileOutputStream("snipes.log",
                                                              true));
            Calendar cal = Calendar.getInstance();
            // If the file is empty, don't print a separator newline.
            if (new File("snipes.log").length() != 0)
            {
                this._file.println();
            }
            this._file.println("----Session: Date: "
                               + new SimpleDateFormat("dd/mm/yyyy").format(cal.getTime())
                               + ", Time: "
                               + new SimpleDateFormat("HH:MM:SS").format(cal.getTime())
                               + "----");
            return true;
        } catch (FileNotFoundException e)
        {
            System.err.println("Unable to initialise logging hook.");
        }
        return false;
    }

    @Override
    public void fini()
    {
        this._file.close();
    }

    private PrintStream _file;

}
