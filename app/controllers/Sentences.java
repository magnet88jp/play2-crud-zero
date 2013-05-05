package controllers;
 
import java.util.List;
 
import play.db.ebean.Model.Finder;
import play.data.Form;
import static play.data.Form.form;
import play.mvc.Controller;
import play.mvc.Result;

import play.Logger;

import models.Sentence;
 
public class Sentences extends Controller {

  public static Result GO_HOME = redirect(
    routes.Sentences.list(0, 0, "")
  );

  public static Result list(Integer page, Integer orderBy, String filter) {
    // 現在のAccountを全検索して表示
    Finder<Long, Sentence> finder = new Finder<Long, Sentence>(Long.class, Sentence.class);
    List<Sentence> sentences = finder.all();

    StringBuilder msg = new StringBuilder();
    msg.append("test");
    msg.append(filter);
    return ok(views.html.Sentences.list.render(msg.toString(), sentences));
  }

  public static Result create() {
    Form<Sentence> sentenceForm = new Form(Sentence.class);
    return ok(views.html.Sentences.createForm.render(sentenceForm));
  }

  public static Result save() {
    Form<Sentence> sentenceForm = form(Sentence.class).bindFromRequest();
    if(sentenceForm.hasErrors()) {
      return badRequest(views.html.Sentences.createForm.render(sentenceForm));
    }
    sentenceForm.get().save();
    flash("success", "Sentence " + sentenceForm.get().name + " has been created");
    return GO_HOME;
  }

  public static Result edit(Long id) {
    Form<Sentence> sentenceForm = form(Sentence.class).fill(
      Sentence.find.byId(id)
    );
    return ok(
      views.html.Sentences.editForm.render(id, sentenceForm)
    );
  }

  public static Result update(Long id) {
    Form<Sentence> sentenceForm = form(Sentence.class).bindFromRequest();
    if(sentenceForm.hasErrors()) {
      return badRequest(views.html.Sentences.editForm.render(id, sentenceForm));
    }
    sentenceForm.get().update(id);
    flash("success", "Sentence " + sentenceForm.get().name + " has been updated");

    return GO_HOME;
  }

  public static Result delete(Long id) {
    Sentence.find.ref(id).delete();
    flash("success", "Sentence has been deleted");
    return GO_HOME;
  }

}