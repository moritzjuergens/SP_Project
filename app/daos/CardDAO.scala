package daos

import scala.util.Success
import model.Card
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import javax.inject.Inject
import scala.concurrent.duration.{Duration, MILLISECONDS}
import scala.concurrent.{Await, ExecutionContext, Future}

class CardDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
                          (implicit executionContext: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  private val Cards = TableQuery[CardTable]

  def all(): Future[Seq[Card]] = db.run(Cards.result)
  def courses(): Future[Seq[Card]] = db.run(Cards.distinctOn(_.course).result)

  def insert(card: Card): Future[Unit] = db.run(Cards += card).map { _ => () }

  private class CardTable(tag: Tag) extends Table[Card](tag, "card") {

    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def course = column[String]("course")
    def question = column[String]("question")
    def answer = column[String]("answer")
    def difficulty = column[String]("difficulty")

    def * = (id.?,course, question, answer, difficulty) <> (Card.tupled, Card.unapply)
  }

}
