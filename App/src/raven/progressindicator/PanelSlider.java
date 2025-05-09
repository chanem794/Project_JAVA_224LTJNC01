package raven.application.form.other;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import net.miginfocom.layout.ComponentWrapper;
import net.miginfocom.layout.LayoutCallback;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import raven.progressindicator.EventSliderAnimatorChanged;

/**
 *
 * @author Raven
 */
public class PanelSlider extends JPanel {

    private Component[] sliderComponent;
    private final Component[] components = new Component[2];
    private final List<EventSliderAnimatorChanged> events = new ArrayList<>();
    private Animator animator;
    private float animate;
    private SliderType type;
    private MigLayout layout;

    public PanelSlider() {
        init();
    }

    private void init() {
        layout = new MigLayout();
        setLayout(layout);
        layout.addLayoutCallback(new LayoutCallback() {
            @Override
            public void correctBounds(ComponentWrapper cw) {
                change(cw);
            }
        });
        animator = new Animator(500, new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                animate = fraction;
                revalidate();
                runEvent(fraction);
            }
        });
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.setResolution(0);
    }

    public void change(ComponentWrapper cw) {
        int width = getWidth();
        int height = getHeight();
        int x = 0;
        int y = 0;
        int x2 = 0;
        int y2 = 0;
        switch (type) {
            case LEFT_TO_RIGHT:
                x = (int) -(width * (1f - animate));
                x2 = (int) (width * animate);
                break;
            case RIGHT_TO_LEFT:
                x = (int) (width - (width * animate));
                x2 = (int) -(width * animate);
                break;
            case TOP_TO_BOTTOM:
                y = (int) -(height * (1f - animate));
                y2 = (int) (height * animate);
                break;
            case BOTTOM_TO_TOP:
                y = (int) (height - (height * animate));
                y2 = (int) -(height * animate);
                break;
            default:
                x2 = width;
                y2 = 0;
        }
        if (cw.getComponent() == components[0]) {
            cw.setBounds(x, y, width, height);
        }
        if (cw.getComponent() == components[1]) {
            cw.setBounds(x2, y2, width, height);
        }
    }

    public boolean isSlidAble() {
        return !animator.isRunning();
    }

    public synchronized void showSlid(Component component, SliderType type) {
        if (!animator.isRunning()) {
            this.type = type;
            if (components[1] != null) {
                remove(components[1]);
            }
            components[1] = components[0];
            components[0] = component;
            add(component, "pos 0 0");
            if (type == SliderType.NONE) {
                animate = 1;
                revalidate();
            } else {
                animator.start();
            }
        }
    }

    public void addEventSliderAnimatorChanged(EventSliderAnimatorChanged event) {
        events.add(event);
    }

    private void runEvent(float f) {
        for (EventSliderAnimatorChanged event : events) {
            event.animatorChange(type, f);
        }
    }

    public static enum SliderType {
        LEFT_TO_RIGHT, RIGHT_TO_LEFT, TOP_TO_BOTTOM, BOTTOM_TO_TOP, NONE
    }

    public Component[] getSliderComponent() {
        return sliderComponent;
    }

    public void setSliderComponent(Component[] sliderComponent) {
        this.sliderComponent = sliderComponent;
    }
}
