//
//  GlobalSheetCoordinator.swift
//  iosApp
//
//  Created by Jan Janovec on 20.01.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import JJNavigation
import shared
import SwiftUI

enum GlobalSheetCoordinator: SheetRoutable {
    case additionalAccountInfo(data: [AdditionalAccountData])
    
    var id: String {
        switch self {
        case .additionalAccountInfo:
            "requiring additional info"
        }
    }
    
    func view(coordinator: SheetCoordinator<GlobalSheetCoordinator>) -> some View {
        switch self {
        case .additionalAccountInfo(let data):
            AdditionalAccountDataView(additionalAccountData: data)
        }
    }
}
