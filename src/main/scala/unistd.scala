import scalanative.native


@native.extern
object unistd {
  def execl(path: native.CString, args: native.CVararg*): native.CInt = native.extern
  def fork(): native.CInt = native.extern
  def getpid(): native.CInt = native.extern
  //def wait(): native.CInt = native.extern
  def waitpid(pid: native.CInt, stat: native.Ptr[native.CInt], options: native.CInt) = native.extern
}
