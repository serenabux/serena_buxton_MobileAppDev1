//
//  ViewController.swift
//  Lab_2 temp
//
//  Created by Serena Buxton on 9/17/18.
//  Copyright © 2018 Serena Buxton. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var seasonLabel: UILabel!
    @IBOutlet weak var seasonImage: UIImageView!
    @IBOutlet weak var pickSeason: UISegmentedControl!
    @IBOutlet weak var capitialSwitch: UISwitch!
    @IBOutlet weak var textSizeSlider: UISlider!
    @IBOutlet weak var fontSizeNumberLabel: UILabel!
    @IBOutlet weak var colorSwitch: UISwitch!
    
    func updateImage(){
        if pickSeason.selectedSegmentIndex==0{
            seasonLabel.text="Winter"
            seasonImage.image=UIImage(named: "winter")
        }
        else if pickSeason.selectedSegmentIndex==1{
            seasonLabel.text="Spring"
            seasonImage.image=UIImage(named: "spring")
        }
        else if pickSeason.selectedSegmentIndex==2{
            seasonLabel.text="Summer"
            seasonImage.image=UIImage(named: "summer")
        }
        else if pickSeason.selectedSegmentIndex==3{
            seasonLabel.text="Fall"
            seasonImage.image=UIImage(named: "fall")
        }
    }
    
    func updateCaps(){
        if capitialSwitch.isOn{
            seasonLabel.text=seasonLabel.text?.uppercased()
        }
        else{
            seasonLabel.text=seasonLabel.text?.lowercased()
        }
    }
    
    func updateColor(){
        if colorSwitch.isOn{
            if pickSeason.selectedSegmentIndex == 0{
                seasonLabel.textColor = UIColor.blue
            }
            else if pickSeason.selectedSegmentIndex == 1{
                seasonLabel.textColor = UIColor.purple
            }
            else if pickSeason.selectedSegmentIndex == 2{
                seasonLabel.textColor=UIColor.green
            }
            else if pickSeason.selectedSegmentIndex == 3{
                seasonLabel.textColor=UIColor.orange
            }
            else{
                seasonLabel.textColor=UIColor.darkGray
            }
            
        }
        else{
            seasonLabel.textColor = UIColor.black
        }
    }
    
    
    @IBAction func changeSeason(_ sender: UISegmentedControl) {
       updateImage()
        updateCaps()
        updateColor()
    }
    @IBAction func changeCapitilize(_ sender: UISwitch) {
       updateCaps()
    }
    @IBAction func changeColor(_ sender: UISwitch) {
        updateColor()
    }
    @IBAction func changeTextSize(_ sender: UISlider) {
        let fontSize = sender.value
        fontSizeNumberLabel.text=String(format: "%.0f", fontSize)
        let fontSizeCGFloat = CGFloat(fontSize)
        seasonLabel.font = UIFont.systemFont(ofSize: fontSizeCGFloat)
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

