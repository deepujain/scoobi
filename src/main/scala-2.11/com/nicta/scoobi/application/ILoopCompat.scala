package com.nicta.scoobi.application

import scala.tools.nsc.interpreter.ILoop

// Without using addThunk we run into deadlock issues in 2.10
// There doesn't appear to be a common function between 2.10 and 2.11 that will preserve the same behaviour
trait ILoopCompat extends ILoop {

  def addThunk(f: => Unit): Unit =
  // Evaluating 'f' directly still works, but the scoobi> prompt is shown immediately after "press Enter"
    intp.initialize(f)
}
