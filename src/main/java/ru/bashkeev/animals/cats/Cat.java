package ru.bashkeev.animals.cats;

public class Cat implements Meowable {
    private String nickName;

    public Cat(String nickName) {
        this.nickName = prepareNickName(nickName);
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = prepareNickName(nickName);
    }

    public String meow(int count) {
        if (count <= 0) {
            return nickName + ": молчит или спит!";
        }

        StringBuilder rsString = new StringBuilder();
        for (int i = 0; i < count; i++) {
            if (i > 0) {
                rsString.append("-");
            }
            rsString.append("мяу");
        }
        rsString.append("!");

        return nickName  + ": " + rsString;
    }

    public void meow() {
        System.out.println(this.meow(1));
    }

    @Override
    public String toString() {
        return "Кот: " + nickName;
    }

    private String prepareNickName (String nickName) {
        if (nickName == null || nickName.isEmpty()) {
            return "Безымянный";
        }

        return nickName.substring(0, 1).toUpperCase() + nickName.substring(1);
    }
}
