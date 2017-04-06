
import scalanative.native

@native.link("readline")
@native.extern
object readline {
  def readline(prompt : native.CString): native.CString = native.extern
  def add_history(line: native.CString): Unit = native.extern
}
