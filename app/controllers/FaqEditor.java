package controllers;
 
import java.util.List;
 
import play.db.ebean.Model.Finder;
import play.data.Form;
import static play.data.Form.form;
import play.mvc.Controller;
import play.mvc.Result;

import play.Logger;

import models.Sentence;
 
public class FaqEditor extends Controller {

  public static Result list(Integer page, Integer orderBy, String filter) {
    
    Finder<Long, Sentence> finder = new Finder<Long, Sentence>(Long.class, Sentence.class);
    List<Sentence> sentences = finder.all();

    StringBuilder msg = new StringBuilder();
    msg.append("test");
    msg.append(filter);
    Form<Sentence> sentenceForm = new Form(Sentence.class);
    return ok(views.html.FaqEditor.list.render(msg.toString(), sentences, sentenceForm));
  }

  public static Result save() {
    Form<Sentence> sentenceForm = form(Sentence.class).bindFromRequest();
    if(sentenceForm.hasErrors()) {
      return badRequest(views.html.Sentences.createForm.render(sentenceForm));
    }
    sentenceForm.get().save();
    flash("success", "Sentence " + sentenceForm.get().name + " has been created");
    return redirect(routes.FaqEditor.list(0, 0, ""));
  }


}