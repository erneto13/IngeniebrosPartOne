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
        Dialog dialog = new Dialog("webos:", skin) {
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
    public void showNPCInteractionDialog(String npc_Name){
        Dialog dialog = new Dialog(npc_Name+" : ", skin) {
            @Override
            public void result(Object obj) {
                this.hide();
                Dialog dialogo;
                dialogo = new Dialog(npc_Name+" dice:",skin);
                dialogo.text(new Label("Ahhhh te mamaste como que por " + obj,skin));
                dialogo.button("Ok",true);
                dialogo.show(stage);
            }
        };

        dialog.text(new Label("¿Por Quien vas a Votar este 2?", skin));
        /*
        dialog.button(new TextButton("Claudia GODSheinbaum", skin), "claudia");
        dialog.button(new TextButton("Xochitl KKGálvez", skin), "xochitl");
        dialog.button(new TextButton("Jajorge Mamaynez", skin), "Mamaynez");
         */
        dialog.button("Claudia", "claudia");
        dialog.button("Xochitl", "xochitl");
        dialog.button("Mamaynez", "maynez");
        dialog.getBackground().setMinHeight(100);
        dialog.getBackground().setMinWidth(150);
        dialog.show(stage);
    }
}

