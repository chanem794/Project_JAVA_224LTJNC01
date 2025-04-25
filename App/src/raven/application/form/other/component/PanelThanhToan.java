
package raven.application.form.other.component;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Component;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import raven.application.form.other.ThanhToanForm;

public class PanelThanhToan extends JPanel {
    public PanelThanhToan() {
        setLayout(new MigLayout("fill, insets 0", "[grow]", "[grow]"));
        putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Login.background;"
                + "arc:20;");

        // Thêm form nhập thực tế vào panel
        ThanhToanForm form = new ThanhToanForm();
        add(form, "grow");
    }
}
