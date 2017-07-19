//
//  CoreDataTests.swift
//  Notes
//
//  Created by Anton Fresher on 19.07.17.
//  Copyright © 2017 Anton Fresher. All rights reserved.
//

import XCTest
import CoreData
@testable import Notes

class CoreDataTests: XCTestCase {
    
    private var manager = CoreDataManager(modelName: "Notes", completion: nil)
    
    private var context: NSManagedObjectContext!
    
    override func setUp() {
        context = manager.createPrivateChildManagedObjectContext()
    }
    
    func testNoteToNoteEntityConversion() {
        let notes = Variables.notes
        let noteEntities = notes.map { NoteEntity(context: context, from: $0) }
        
        let notesFromNoteEntities = noteEntities.map { $0.toNote()! }
        
        XCTAssertEqual(notes, notesFromNoteEntities)
    }
    
    func testNotebookToNotebookEntityConversion() {
        let notebook = Variables.notebook
        let notebookEntity = NotebookEntity(context: context, from: notebook)
        
        let notebookFromNotebookEntityOptional = notebookEntity.toNotebook()
        
        guard let notebookFromNotebookEntity = notebookFromNotebookEntityOptional else {
            XCTFail()
            return
        }
        
        for note in notebook { XCTAssertTrue(notebookFromNotebookEntity.contains(note)) }
        for note in notebookFromNotebookEntity { XCTAssertTrue(notebook.contains(note)) }
    }
}
