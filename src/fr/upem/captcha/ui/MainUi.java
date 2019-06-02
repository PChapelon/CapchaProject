package fr.upem.captcha.ui;

import java.io.IOException;


/*

compilation (projet exemple)
javac -d bin -sourcepath src src/fr/upem/captcha/ui/MainUi.java

exécution
java -cp bin fr.upem.captcha.ui.MainUi
 */


/**
 * @author paulchapelon
 * Main class of the program
 */

public class MainUi {

	public static void main(String[] args) throws IOException {
		WindowCapcha window = new WindowCapcha("AreYouARobot");
		try {
			window.initFrame();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}