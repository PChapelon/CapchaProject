package fr.upem.captcha.ui;

import java.io.IOException;


/*

compilation (projet exemple)
javac -d classes -sourcepath src src/fr/upem/capcha/ui/MainUi.java

exécution
java -cp classes fr.upem.capcha.ui.MainUi
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