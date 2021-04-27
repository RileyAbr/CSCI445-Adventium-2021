# CSCI445-Adventium-2021

This Eclipse plug-in simplifies the extension of AADL models with AGREE constraints through the use of an error-reducing UI. This project was created for **CSCI 445: Software Projects Capstone**.

---

## Environment Setup

Your environment will need to have the following features installed:

- [Java SDK 1.8](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)

- [Eclipse Installer](https://www.eclipse.org/downloads/packages/installer)

- OSATE
  1. [OSATE 2.7.0](https://osate-build.sei.cmu.edu/download/osate/stable/2.7.0/)

  2. [AGREE plugin 2.5.2-RELEASE](https://github.com/loonwerks/AGREE/releases/tag/2.5.2-RELEASE)

---

## Installation

Once the environment is set up, follow these steps:

1. Download [Plugin](https://github.com/RileyAbr/CSCI445-Adventium-2021/blob/main/AGREE_Statement_Builder_Download.zip)
2. Extract the file downloaded
3. Click "Install New Software" in the "Help" tab in OSATE
![Install New Software](/screenshots/installation_help_install.png)
4. In the Install window click "Add..."
![Add](/screenshots/installation_install_window.png)
5. Select Local and select the extracted file.
![Local](/screenshots/add_local.png)
6. Add a name and Click "Add"!
7. Check the box on AGREE_Statement_Builder
8. Uncheck the Box “Contact all update sites during install to find required software”
9. Click "Next"
![AGREE Statement Builder](/screenshots/check_agree.png)
10. Accept the Licensing Agreement and Click "Finish"
![Accept Licensing Agreement](/screenshots/accept_license.png)
11. Click "Install anyway"
![Install Anyway](/screenshots/install_anyway.png)
12. Click "Restart Now"
![Restart Now](/screenshots/restart_now.png)
13. Plugin tab should be visible on restart.
![Now Available](/screenshots/now_available.png)

---

## Usage

Follow these steps to properly run the plug-in:

1. Specify the file path of the project:
![Specify Directory](/screenshots/directory_spec.png)
2. Import an AADL file into the Eclipse workspace.
    - Default path for existing model:
      1. Right click the AADL Navigator.
      ![Right Click](/screenshots/right_click.png)
      2. Click "Import...".
      3. Open General->Projects from Folder or Archive in the Import Wizard.
      ![Import Wizard](/screenshots/import_wizard.png)
      4. Click the "Directory" button and select the file path in the newly opened window.
      5. Click the "Finish" button
3. Click on the AADL file so that it is highlighted and selected in the project explorer.
4. Click the "Check Model" tab in the toolbar at the top of the editor.
![Now Available](/screenshots/now_available.png)
5. Follow the labels in the UI until you reach the "Output" screen. You have two options in both the "Assumptions" screen and the "Guarantees" screen:
    1. Use the dropdown menus with the variables and operators predefined.
    ![Assumptions Custom Button](/screenshots/assumptions_custom.png)
    ![Guarantees Custom Button](/screenshots/guarantees_custom.png)
    2. Click the "Custom Statement" button if you have a more complex constraints to add to the AGREE model.
    ![Assumptions Custom Open](/screenshots/assumptions_custom_open.png)
6. Copy and paste the "Output" into the selected model.
![Output](/screenshots/output.png)
7. Click "Finish" then click "Okay" in the "Success" window.
![Success](/screenshots/successful_write.png)

---

## Demo

You can view the following demonstration materials to see this OSATE plug-in in action.

### Demo Video

[Demo Video]()

### UI Prototype

This project also contains a UI Prototype. This prototype was built during the first sprint for the project, in order to gather feedback from project stakeholders on appearance and workflow. The prototype was developed as a React app. The visual design was created to mimic the default Eclipse style. 

The prototype can be viewed live on Netlify at: [https://adventium-gumbo-ui-prototype.netlify.app/](https://adventium-gumbo-ui-prototype.netlify.app/)
