package controllers;
 
import java.util.List;
 
import play.db.ebean.Model.Finder;
import play.data.Form;
import static play.data.Form.form;
import play.mvc.Controller;
import play.mvc.Result;

import play.Logger;

import models.Account;
 
public class Accounts extends Controller {

  public static Result GO_HOME = redirect(
    routes.Accounts.list(0, 0, "")
  );

  public static Result list(Integer page, Integer orderBy, String filter) {
    // 現在のAccountを全検索して表示
    Finder<Long, Account> finder = new Finder<Long, Account>(Long.class, Account.class);
    List<Account> accounts = finder.all();

    StringBuilder msg = new StringBuilder();
    msg.append("test");
    msg.append(filter);
    return ok(views.html.Accounts.list.render(msg.toString(), accounts));
  }

  public static Result create() {
    Form<Account> accountForm = new Form(Account.class);
    return ok(views.html.Accounts.createForm.render(accountForm));
  }

  public static Result save() {
    Form<Account> accountForm = form(Account.class).bindFromRequest();
    if(accountForm.hasErrors()) {
      return badRequest(views.html.Accounts.createForm.render(accountForm));
    }
    accountForm.get().save();
    flash("success", "Account " + accountForm.get().name + " has been created");
    return GO_HOME;
  }

  public static Result edit(Long id) {
    Form<Account> accountForm = form(Account.class).fill(
      Account.find.byId(id)
    );
    return ok(
      views.html.Accounts.editForm.render(id, accountForm)
    );
  }

  public static Result update(Long id) {
    Logger.info("debug desu2=" + id.toString());
    
    Form<Account> accountForm = form(Account.class).bindFromRequest();
    if(accountForm.hasErrors()) {
      return badRequest(views.html.Accounts.editForm.render(id, accountForm));
    }
    accountForm.get().update(id);
    flash("success", "Account " + accountForm.get().name + " has been updated");

    return GO_HOME;
  }

  public static Result delete(Long id) {
    Account.find.ref(id).delete();
    flash("success", "Account has been deleted");
    return GO_HOME;
  }

}