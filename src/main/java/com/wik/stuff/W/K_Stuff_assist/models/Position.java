package com.wik.stuff.W.K_Stuff_assist.models;

public enum Position {
   TRAINEE_ASSISTANT_WAITER("Стажер помощник официанта"),
   TRAINEE_WAITER("Стажер официант"),
   WAITER("Официант"),
   ASSISTANT_WAITER("Помощник официанта"),
   MANAGER("Управляющий");

   private final String displayName;

   Position(String displayName) {
      this.displayName = displayName;
   }

   public String getDisplayName() {
      return displayName;
   }
}