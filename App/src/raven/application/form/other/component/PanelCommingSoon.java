/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raven.application.form.other.component;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
/**
 *
 * @author dinhk
 */
public class PanelCommingSoon extends JPanel{

    public PanelCommingSoon() {
        setLayout(new MigLayout("filly,insets 40 20 40 20, width 720", "[]40[]", "[fill]"));
        putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Login.background;"
                + "arc:20;");
    }
    
}
