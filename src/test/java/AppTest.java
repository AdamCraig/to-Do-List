import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Rule
  public ClearRule clearRule = new ClearRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Todo list!");
    assertThat(pageSource()).contains("View Category List");
    assertThat(pageSource()).contains("Add a New Category");
  }

  @Test
  public void categoryIsCreatedTest() {
    goTo("http://localhost:4567/");
    click("a", withText("Add a New Category"));
    fill("#name").with("Household chores");
    submit(".btn");
    assertThat(pageSource()).contains("Your category has been saved.");
  }

  @Test
  public void categoryIsDisplayedTest() {
    goTo("http://localhost:4567/categories/new");
    fill("#name").with("Household chores");
    submit(".btn");
    click("a", withText("View categories"));
    assertThat(pageSource()).contains("Household chores");
  }

  @Test
  public void categoryShowPageDisplaysName() {
    goTo("http://localhost:4567/categories/new");
    fill("#name").with("Household chores");
    submit(".btn");
    click("a", withText("View categories"));
    click("a", withText("Household chores"));
    assertThat(pageSource()).contains("Household chores");
  }
}
