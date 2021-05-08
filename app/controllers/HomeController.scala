package controllers

import play.api.mvc._
//import play.api.db._
import javax.inject.{Inject, _}
import daos.ProduktDAO
import model.Produkt
import play.api._
import play.api.data.Form
import play.api.data.Forms.{mapping, number, text}
import scala.concurrent.ExecutionContext

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(produktDao: ProduktDAO, controllerComponents: ControllerComponents)
                              (implicit executionContext: ExecutionContext) extends AbstractController(controllerComponents) {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action.async {
    produktDao.all().map { case (produkte) => Ok(views.html.index(produkte)) }
  }

  def hello(name: String) = Action {
    Ok(views.html.hello(name))
  }

  def topics() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.topics())
  }

  def about() = Action {
    Ok(views.html.about())
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
}
