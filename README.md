# BaseProject

This is the base Android project. It is a good idea to use it as starting point instead of creating a new project.

I will explain you why:

* It implements selectors for lollipop and pre-lollipop devices. It is a good idea to have the same selectors regardless of user's device. This code implements custom selector color for list items, action items, settings, etc. 
* It implements Toolbar in the correct way. Android documentation has a mistake in the toolbar example, a toolbar in the example doesn't change his size in album orientation.
* It contains attributes for palette and text colors.
* It contains the most used dependencies.
* Build scripts to rename apk, to sign release builds using a dialog or a file with information about release key.
* Examples of usage for [Utils](https://github.com/evgeniysharafan/Utils) library.
