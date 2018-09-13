//
//  ViewController.swift
//  Lab 1
//
//  Created by Serena Buxton on 9/8/18.
//  Copyright © 2018 Serena Buxton. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    var current=0
    var pictures:[String]=["yogurt","grill", "final"]
    var stepInstruction:[String]=["Combine 1/4 cup of Greek yogurt and 1/8 tbs of cinnamon.", "Cut 2 peaches in half. Grill the peaches on low until soft, about 2-4 minutes on each side.","Drizzle with honey and serve each with 1 tbsp of yogurt."]
    @IBOutlet weak var stepImage: UIImageView!
    @IBOutlet weak var Instructions: UILabel!
    @IBAction func changeStep(_ sender: UIButton) {
        if sender.tag==1{
            current=(current+1)%3
            stepImage.image=UIImage(named: pictures[current])
            Instructions.text=stepInstruction[current]
        }
        else if sender.tag==2{
            //current=(current-1)%3
            current=current-1
            if current<0{
                current=2
            }
            stepImage.image=UIImage(named: pictures[current])
            Instructions.text=stepInstruction[current]
        }
        
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

