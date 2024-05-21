package com.pascal.game.Conversations;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.pascal.game.Quests.QuestTexts;

public class ConversationHandler {
    private Skin skin;
    private Stage stage;

    public ConversationHandler(Skin skin, Stage stage) {
        this.skin = skin;
        this.stage = stage;
    }

    public void showNPCInteractionDialog() {
        Dialog dialog = new Dialog("Figueroa:", skin) {
            public void result(Object obj) {
                System.out.println("Respuesta: " + obj);
            }
        };

        dialog.text(new Label(QuestTexts.NPC_INTERACTION_TEXT, skin));
        dialog.button(new TextButton("Claudia GODSheinbaum", skin), "claudia");
        dialog.button(new TextButton("Xochitl KKGálvez", skin), "xochitl");
        dialog.button(new TextButton("Jajorge Mamaynez", skin), "xd");
        dialog.show(stage);
    }
}

