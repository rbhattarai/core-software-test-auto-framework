package contracts;

public interface IComponent extends IButton, ICheckbox, IDropdown, ITextbox {
    IComponent getComponent();
}
