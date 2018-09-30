//
//  ViewController.swift
//  Lab 3
//
//  Created by Serena Buxton on 9/27/18.
//  Copyright © 2018 Serena Buxton. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UITextFieldDelegate {

    @IBOutlet weak var numeratorInput: UITextField!
    @IBOutlet weak var denominatorInput: UITextField!
    @IBOutlet weak var answerLabel: UILabel!
    
    @IBAction func onTapGestureRecognized(_ sender: Any) {
        numeratorInput.resignFirstResponder() //tells to yeild first responder status if they have it
        denominatorInput.resignFirstResponder()
        divide()
    }
    func divide(){
        var num:Float=1
        var denom:Float=1
        var dontDivide:Bool = false
        var value:Float
        if numeratorInput.text!.isEmpty{
            num=0
        }
        else{
            num=Float(numeratorInput.text!)!
        }
        if denominatorInput.text!.isEmpty{
            denom=1
            
        }
        else{
            denom=Float(denominatorInput.text!)!
            if denom==0{
                dontDivide = true
                let alert=UIAlertController(title: "Attention", message: "The denominator cannot be 0!",preferredStyle:UIAlertControllerStyle.alert)
                let okAction = UIAlertAction(title: "Ok", style: UIAlertActionStyle.default, handler: {action in
                                                                                                self.denominatorInput.text="1"
                    self.divide()})
                alert.addAction(okAction)
                present(alert, animated:true,completion: nil)
            }
        }
        if !dontDivide{
            value=num/denom
            self.answerLabel.text=String(value)
        }
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool{
        textField.resignFirstResponder()
        return true
    }
    
    func textFieldDidEndEditing(_ textField: UITextField) {
        divide()
    }
    
    override func viewDidLoad() {
        numeratorInput.delegate=self
        denominatorInput.delegate=self
        answerLabel.text=""
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

