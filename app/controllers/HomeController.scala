package controllers

import play.api.mvc._

import java.time.Duration
import scala.concurrent.Await
import scala.concurrent.duration.{Duration, MILLISECONDS}
//import play.api.db._
import javax.inject.{Inject, _}
import daos.ProduktDAO
import daos.CardDAO
import model.Produkt
import model.Card
import play.api._
import play.api.data.Form
import play.api.data.Forms.{mapping, number, text}
import scala.concurrent.ExecutionContext

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(produktDao: ProduktDAO,cardDAO: CardDAO, controllerComponents: ControllerComponents)
                              (implicit executionContext: ExecutionContext) extends AbstractController(controllerComponents) {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action.async {

    cardDAO.all().map {
      case (cards) =>  Ok(views.html.index(cards))
    }

//    produktDao.all().map {case (produkte => Ok(views.html.index(produkte))}
  }

  def hello(name: String) = Action {
    Ok(views.html.hello(name))
  }

  def courses() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.courses())
  }

  def quiz() = Action.async{
    cardDAO.all().map { case (cards) => Ok(views.html.quiz(cards)) }
  }

  def env() = Action { implicit request: Request[AnyContent] =>
    Ok("Nothing to see here")
    //Ok(System.getenv("JDBC_DATABASE_URL"))
  }

  val produktForm = Form(
    mapping(
      "name" -> text(),
      "price" -> number())(Produkt.apply)(Produkt.unapply))

  def insertProdukt = Action.async { implicit request =>
    val produkt: Produkt = produktForm.bindFromRequest.get
    produktDao.insert(produkt).map(_ => Redirect(routes.HomeController.index))
  }
  val cardForm = Form(
    mapping(
      "id" -> number(),
      "course" -> text(),
      "question" -> text(),
      "answer" -> text(),
      "difficulty" -> text())(Card.apply)(Card.unapply))

  def insertCard = Action.async { implicit request =>
    val card: Card = cardForm.bindFromRequest.get
    cardDAO.insert(card).map(_ => Redirect(routes.HomeController.index))
  }
}
