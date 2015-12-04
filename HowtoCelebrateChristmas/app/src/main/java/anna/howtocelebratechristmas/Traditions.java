package anna.howtocelebratechristmas;

public enum Traditions {
    Choice_1(R.string.Tradition_Choice_1),
    Choice_2(R.string.Tradition_Choice_2),
    Choice_3(R.string.Tradition_Choice_3),
    Choice_6(R.string.Tradition_Choice_5),
    Choice_7(R.string.Tradition_Choice_6),
    Choice_8(R.string.Tradition_Choice_7),
    ;

    public int titleResId;

    Traditions(int titleResId) {
        this.titleResId = titleResId;
    }

}
