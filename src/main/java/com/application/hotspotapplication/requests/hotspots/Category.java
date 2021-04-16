package com.application.hotspotapplication.requests.hotspots;

public enum Category {
    ACCIDENT(1),
    CORONA(2),
    HIJACKING(3),
    ROADBLOCK(4),
    METROPOLICE(5),
    MUGGING(6);

    public final int categoryId;

    private Category(int categoryId){
        this.categoryId = categoryId;
    }
}
