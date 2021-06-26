package com.github.adriens.imgflip.sdk.imgflip.sdk.domain.enums;

public enum RankIcon {

    ICO0("ico0", "https://imgflip.com/icons/anon.svg"),
    ICO1("ico1", "https://imgflip.com/icons/anon-white.svg"),
    ICO2("ico2", "https://imgflip.com/icons/anon-yellow.svg"),
    ICO3("ico3", "https://imgflip.com/icons/anon-orange.svg"),
    ICO4("ico4", "https://imgflip.com/icons/anon-blue.svg"),
    ICO5("ico5", "https://imgflip.com/icons/star-black.svg"),
    ICO6("ico6", "https://imgflip.com/icons/star-black-white.svg"),
    ICO7("ico7", "https://imgflip.com/icons/star-black-yellow.svg"),
    ICO8("ico8", "https://imgflip.com/icons/star-black-orange.svg"),
    ICO9("ico9", "https://imgflip.com/icons/star-black-blue.svg"),
    ICO10("ico10", "https://imgflip.com/icons/star-black-rainbow.svg"),
    ICO11("ico11", "https://imgflip.com/icons/10k.svg"),
    ICO12("ico12", "https://imgflip.com/icons/lol.svg"),
    ICO13("ico13", "https://imgflip.com/icons/star-black-black-rainbow.svg"),
    ICO14("ico14", "https://imgflip.com/icons/star-black-white-rainbow.svg"),
    ICO15("ico15", "https://imgflip.com/icons/star-black-yellow-rainbow.svg"),
    ICO16("ico16", "https://imgflip.com/icons/star-black-orange-rainbow.svg"),
    ICO17("ico17", "https://imgflip.com/icons/star-black-blue-rainbow.svg"),
    ICO18("ico18", "https://imgflip.com/icons/hammer-white.svg"),
    ICO19("ico19", "https://imgflip.com/icons/hammer-yellow.svg"),
    ICO20("ico20", "https://imgflip.com/icons/hammer-orange.svg"),
    ICO21("ico21", "https://imgflip.com/icons/hammer-blue.svg"),
    ICO22("ico22", "https://imgflip.com/icons/crown-white.svg"),
    ICO23("ico23", "https://imgflip.com/icons/crown-yellow.svg"),
    ICO24("ico24", "https://imgflip.com/icons/crown-orange.svg"),
    ICO25("ico25", "https://imgflip.com/icons/crown-blue.svg"),
    ICO26("ico26", "https://imgflip.com/icons/advice.svg"),
    ICO27("ico27", "https://imgflip.com/icons/starnest-white.svg"),
    ICO28("ico28", "https://imgflip.com/icons/starnest-yellow.svg"),
    ICO29("ico29", "https://imgflip.com/icons/starnest-orange.svg"),
    ICO30("ico30", "https://imgflip.com/icons/starnest-blue.svg"),
    ICO31("ico31", "https://imgflip.com/icons/stars-white.svg"),
    ICO32("ico32", "https://imgflip.com/icons/stars-yellow.svg"),
    ICO33("ico33", "https://imgflip.com/icons/stars-orange.svg"),
    ICO34("ico34", "https://imgflip.com/icons/stars-blue.svg"),
    ICO35("ico35", "https://imgflip.com/icons/starburst.svg"),
    ICO36("ico36", "https://imgflip.com/icons/binary-white.svg"),
    ICO37("ico37", "https://imgflip.com/icons/binary-yellow.svg"),
    ICO38("ico38", "https://imgflip.com/icons/binary-orange.svg"),
    ICO39("ico39", "https://imgflip.com/icons/binary-blue.svg"),
    ICO40("ico40", "https://imgflip.com/icons/shooting-star-white.svg"),
    ICO41("ico41", "https://imgflip.com/icons/shooting-star-yellow.svg"),
    ICO42("ico42", "https://imgflip.com/icons/shooting-star-orange.svg"),
    ICO43("ico43", "https://imgflip.com/icons/shooting-star-blue.svg"),
    ICO46("ico46", "https://imgflip.com/icons/binary-green.svg");

    public final String id;

    public final String url;

    RankIcon(String id, String url) {
        this.id = id;
        this.url = url;
    }

    public static RankIcon urlForId(String id) {
        for (RankIcon rankIcon : values()) {
            if (rankIcon.id.equals(id))
                return rankIcon;
        }

        return null;
    }

    @Override
    public String toString() {
        return "RankIcon{" +
                "id='" + id + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
