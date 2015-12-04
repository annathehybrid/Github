package anna.howtocelebratechristmas;

public enum Traditions {
    Choice_1(R.string.Tradition_Choice_1),
    Choice_2(R.string.Tradition_Choice_2),
    Choice_3(R.string.Tradition_Choice_3),
    ;

    public int titleResId;

    Traditions(int titleResId) {
        this.titleResId = titleResId;
    }

}
