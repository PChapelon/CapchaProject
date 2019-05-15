package fr.upem.capcha.ui;

import java.io.IOException;


/*

compilation (projet exemple)
javac -d classes -sourcepath src src/fr/upem/capcha/ui/MainUi.java

exécution
java -cp classes fr.upem.capcha.ui.MainUi
 */

public class MainUi {

	public static void main(String[] args) throws IOException {
		System.out.println(" coucou");
		WindowCapcha window = new WindowCapcha("AreYouARobot");
		window.initFrame();
	}
}