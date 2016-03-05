package adf.page;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.model.Model;

/**
 * Source taken from https://cwiki.apache.org/WICKET/getting-user-confirmation.html
 * @author miel
 */
public class JavaScriptEventConfirmation extends AttributeModifier {
	public JavaScriptEventConfirmation(String event, String msg) {
		super(event, true, new Model(msg));
	}

        @Override
	protected String newValue(final String currentValue, final String replacementValue) {
		String prefix = "var conf = confirm('" + replacementValue + "'); " +
			"if (!conf) return false; ";
		String result = prefix;
		if (currentValue != null) {
			result = prefix + currentValue;
		}
		return result;
	}
}