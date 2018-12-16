package mdc

case class Quiz(quiz_id: Long, questions: Seq[Question])
case class Question(question_id: String, answer: String, isCorrect: Boolean)
case class Quizzes(quizzes: Seq[Quiz])

