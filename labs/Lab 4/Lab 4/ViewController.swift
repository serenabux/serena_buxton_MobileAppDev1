//
//  ViewController.swift
//  Lab 4
//
//  Created by Serena Buxton on 10/5/18.
//  Copyright © 2018 Serena Buxton. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    @IBOutlet weak var pastWorry: UILabel!
    @IBOutlet weak var pastReason: UILabel!
    var user=Worry()
    let filename = "worry.plist"
    
    func dataFileURL(_ filename:String) -> URL? {
    //returs an array of URLs for the document directory in the user;s home directory
    let urls = FileManager.default.urls(for: .documentDirectory, in: .userDomainMask)
        var url : URL?
        //append the file name to the first item in the array which is the document directory
        url = urls.first?.appendingPathComponent(filename)
        //return the URL of the data file or nil if it does not exist
        return url
    }

    @IBAction func unwindSegue(_ segue:UIStoryboardSegue){
        pastWorry.text=user.currentWorry
        pastReason.text=user.currentReason
    }

    override func viewDidLoad() {
        //url of data file
        let fileURL = dataFileURL(filename)
        
        //if the data file exists, use it
        if FileManager.default.fileExists(atPath: (fileURL?.path)!){
            let url = fileURL!
            do{
                //creates a data buffer with the contents of the plist
                let data = try Data(contentsOf: url)
                //create an instance of the PropertyListDecode
                let decoder = PropertyListDecoder()
                //decode the data using the structure of the Worry class
                user = try decoder.decode(Worry.self, from: data)
                //asign data to textfields
                
            } catch{
                print ("no file")
            }
        }
        else{
            print ("file does not exist")
        }
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }
//called when the UIApplicationWillResignActiveNotification notification is posted
//all notification methods take a single NSNotification intance as their argument
    @objc func applicationWillResignActive(_ notification: Notification){
        //url of data file
        let fileURL = dataFileURL(filename)
        //create an instance of PropertyListEncoder
        let encoder = PropertyListEncoder()
        //set format type to xml
        encoder.outputFormat = .xml
        do{
            //encode data using the structure of the Worry class
            let plistData = try encoder.encode(user)
            //write encoded data to the file
            try plistData.write(to: fileURL!)
        }
        catch{
            print("write error")
        }
    }
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

