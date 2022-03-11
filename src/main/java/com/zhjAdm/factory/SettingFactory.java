package com.zhjAdm.factory;

import com.intellij.openapi.options.SearchableConfigurable;
import com.zhjAdm.ui.ReadBookSettingForm;
import com.zhjAdm.util.SimpleConfigUtil;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class SettingFactory implements SearchableConfigurable {

    private ReadBookSettingForm settingUI = new ReadBookSettingForm();

    @Override
    public @NotNull String getId() {
        return "ReadBook.id";
    }

    @Override
    public @Nls(capitalization = Nls.Capitalization.Title) String getDisplayName() {
        return "ReadBook-config";
    }

    @Override
    public @Nullable
    JComponent createComponent() {
        return settingUI.getComponent();
    }

    @Override
    public boolean isModified() {
        return true;
    }

    @Override
    public void apply() {
        String filePath = settingUI.getUrlTextField().getText();
        String length = settingUI.getLengthField().getText();
        String row = settingUI.getRowField().getText();
        String readTime = settingUI.getReadTimeField().getText();
        SimpleConfigUtil.save("length",length);
        SimpleConfigUtil.save("filePath",filePath);
        SimpleConfigUtil.save("row",row);
        SimpleConfigUtil.save("nextCursor",1);
        SimpleConfigUtil.save("readTime",readTime);
    }

}