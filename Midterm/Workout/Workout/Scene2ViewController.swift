//
//  Scene2ViewController.swift
//  Workout
//
//  Created by Serena Buxton on 10/18/18.
//  Copyright © 2018 Serena Buxton. All rights reserved.
//

import UIKit

class Scene2ViewController: UIViewController, UITextFieldDelegate {

    @IBOutlet weak var done: UIBarButtonItem!
    @IBOutlet weak var email: UITextField!
    @IBOutlet weak var name: UITextField!
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
    
    func textFieldDidEndEditing(_ textField: UITextField) {
        return
    }
    
    override func viewDidLoad() {
        name.delegate = self
        email.delegate = self
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

    
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
        if segue.identifier == "done"{
            let scene1ViewController = segue.destination as! ViewController
            if name.text!.isEmpty == false{
                scene1ViewController.user.name = name.text
            }
            if email.text!.isEmpty == false{
                scene1ViewController.user.email = email.text
            }
        }
    }
    

}
