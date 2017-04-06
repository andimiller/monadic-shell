object Fork {
  object Parent {
    @inline def unapply(pid: Int): Option[Int] = Option(pid).filter(_ != 0)
  }
  object Child {
    @inline def unapply(pid: Int): Boolean = pid == 0
  }
}
