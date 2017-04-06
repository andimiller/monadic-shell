import scalanative.native._
import scalanative.native

object Main {

  implicit class UsefulString(s: String) {
    @inline def toC = toCString(s)
  }

  implicit class UsefulCString(s: native.CString) {
    @inline def toScala = native.fromCString(s)
  }

  import Fork._

  def runCommand(commands: List[String]) = {
    unistd.fork() match {
      case Parent(childpid) =>
        println("waiting for children")
        unistd.waitpid(-1, null, 0) // wait for all children
        println("all children exited")
      case Child() =>
        val (command :: args) = commands
        val cargs = args.map(_.toC) :+ 0.cast[native.CString]
        val returncode = unistd.execl(command.toC, args)
        println(s"the child returned $returncode")
    }
  }

  def main(args: Array[String]): Unit = {
    println("starting up")
    var exit = false
    while (!exit) {
      val input = readline.readline("# ".toC).toScala.split(' ').toList
      println(input)
      runCommand(input)
    }
    /*
    val window = ncurses.initscr()
    ncurses.wprintw(window, c"hello world! %s", c"woo!")
    ncurses.refresh()
    ncurses.wgetch(window)
    ncurses.endwin()
   */
  }

  def forkit(command: List[String]) = {}

}
