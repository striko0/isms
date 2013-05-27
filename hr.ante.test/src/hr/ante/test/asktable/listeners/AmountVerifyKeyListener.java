package hr.ante.test.asktable.listeners;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.swt.custom.VerifyKeyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Text;

public final class AmountVerifyKeyListener implements VerifyListener, VerifyKeyListener             {

    private static final String REGEX = "^[-+]?[0-9]*[,]?[0-9]{0,2}+$";

    private static final Pattern pattern = Pattern.compile(REGEX);

    public void verifyText(VerifyEvent verifyevent) {
        verify(verifyevent);
    }

    public void verifyKey(VerifyEvent verifyevent) {
        verify(verifyevent);
    }

    private void verify (VerifyEvent e) {
        String string = e.text;
        char[] chars = new char[string.length()];
        string.getChars(0, chars.length, chars, 0);

        Text text = (Text)e.getSource();

        if ( ( ",".equals(string) || ".".equals(string) ) && text.getText().indexOf(',') >= 0 ) {
            e.doit = false;
            return;
        }

        for (int i = 0; i < chars.length; i++) {
            if (!(('0' <= chars[i] && chars[i] <= '9') || chars[i] == '.' ||  chars[i] == ',' || chars[i] == '-')) {
                e.doit = false;
                return;
            }

//            if ( chars[i] == ',' ) {
//                chars[i] = '.';
//            }
        }



//        final String oldS = text.getText();
        final String oldS = text.getText();
        String newS = (oldS.substring(0, e.start) + e.text + oldS.substring(e.end));
        Matcher matcher = pattern.matcher(newS);
        if ( !matcher.matches() ) {

            e.doit = false;
            return;
        }


    }
}
