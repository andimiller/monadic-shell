import scalanative.native._

object Main {

  implicit class UsefulString(s: String) {
    @inline def toC = toCString(s)
  }

  implicit class UsefulCString(s: CString) {
    @inline def toScala = fromCString(s)
  }

  import Fork._

  def runCommand(command: Array[CString]): CInt = {
    unistd.fork() match {
      case Parent(childpid) =>
        var result = stackalloc[CInt](1)
        unistd.waitall(result)
        println(!result)
        !result
      case Child() =>
        val arrayargs = stackalloc[CString](command.length).cast[Ptr[CString]]
        for (idx <- 1 until command.length) {
          !(arrayargs + (idx-1)) = command(idx).cast[Ptr[CChar]]
        }
        !(arrayargs + command.size) = 0.cast[CString]
        unistd.execv(command(0), arrayargs)
        -1
    }
  }

  def main(args: Array[String]): Unit = {
    var exit = false
    var lastReturnCode: Option[Int] = None
    while (!exit) {
      val prompt = lastReturnCode match {
        case None => "# "
        case Some(x) if x==0 => Colours.GREEN + "# " + Colours.DEFAULT
        case Some(x) if x!=0 => Colours.RED   + "# " + Colours.DEFAULT
      }
      val input = readline.readline(prompt.toC)
      val splitInput = input.toScala.split(' ')
      val returncode = splitInput match {
        case x if x(0) == "exit" => sys.exit(0)
        case _ =>
          runCommand(splitInput.map(_.toC))
      }
      lastReturnCode = Some(returncode)
      if (returncode==0) {
        readline.add_history(input)
      }
    }
  }
}
