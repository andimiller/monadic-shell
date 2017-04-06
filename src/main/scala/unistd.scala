import scala.scalanative.native._

@extern
object unistd {
  def execl(path: CString, args: CVararg*): CInt =
    extern
  def execv(path: CString, args: Ptr[CString]): CInt = extern
  def fork(): CInt = extern
  def getpid(): CInt = extern
  @name("wait") def waitall(stat: Ptr[CInt]): CInt = extern
  def waitpid(pid: CInt,
              stat: Ptr[CInt],
              options: CInt) = extern
}
