import scalanative.native


@native.link("ncurses")
@native.extern
object ncurses {
  type WINDOW = native.Ptr[Byte] // this is a void*
  def initscr(): WINDOW = native.extern
  def raw(): native.CInt = native.extern
  def noraw(): native.CInt = native.extern
  def echo(): native.CInt = native.extern
  def noecho(): native.CInt = native.extern
  def keypad(w: WINDOW, v: native.CInt): native.CInt = native.extern
  def wprintw(w: WINDOW, s: native.CString, args: native.CVararg*): Unit = native.extern
  def printw(s: native.CString, args: native.CVararg*): Unit = native.extern
  def refresh(): Unit = native.extern
  def wgetch(w: WINDOW): native.CInt = native.extern
  def getch(): native.CInt = native.extern
  def endwin(): Unit = native.extern
}