package regkalk2.kszajgapp.hu.regkalk;

/**
 * Created by Csiga on 2018. 01. 31..
 */

public class UIElementState {
    private int name;
    private int value;

    public UIElementState(int name, int value) {
        this.name = name;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
