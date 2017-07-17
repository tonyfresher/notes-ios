//
//  NotebookTests.swift
//  NotesTests
//
//  Created by Anton Fresher on 11.07.17.
//  Copyright © 2017 Anton Fresher. All rights reserved.
//

import XCTest
@testable import Notes

class NotebookTests: XCTestCase {
    
    private var notebook = Notebook(from: [
        Note(title: "Foo0", content: "Bar"),
        Note(title: "Foo1", content: "Bar", color: Note.defaultColor),
        Note(title: "Foo2", content: "Bar", color: UIColor(hexString: "#000000")!),
        Note(title: "Foo3", content: "Bar", erasureDate: Date()),
        Note(title: "Foo4", content: "Bar", color: UIColor(hexString: "#000000")!, erasureDate: Date())
        ])
    
    func testBasicManipulations() {
        var note = Note()
        
        // test "add"
        notebook.add(note: note)
        
        // test "contains"
        let contains: (Note) -> Bool = { self.notebook.contains(with: $0.uuid) }
        XCTAssertTrue(contains(note))
        
        // test "update"
        note.title = "Foo"
        notebook.update(note: note)
        XCTAssertTrue(contains(note))
        XCTAssertEqual(notebook.first { $0.uuid == note.uuid }!.title, "Foo")
        
        // test "remove"
        let removed = try! notebook.remove(with: note.uuid)
        XCTAssertFalse(contains(note))
        XCTAssertEqual(note, removed)
    }
    
    func testSaveAndLoadFromFile() {
        let filename = "notes"
        
        let path = try? notebook.save(to: filename)
        if path != nil {
            let loaded = Notebook.load(from: filename)
            XCTAssertEqual(loaded!, notebook)
        } else {
            XCTFail()
        }
    }
}
