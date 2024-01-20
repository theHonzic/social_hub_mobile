//
//  AdditionalAccountDataView.swift
//  iosApp
//
//  Created by Jan Janovec on 20.01.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import shared
import SwiftUI

struct AdditionalAccountDataView: View {
    @StateObject private var viewModel: AdditionalAccountDataVM
    
    init(additionalAccountData: [AdditionalAccountData]) {
        self._viewModel = .init(wrappedValue: .init(additionalAccountData: additionalAccountData))
    }
    
    var body: some View {
        Group {
            if let step = viewModel.currentStep {
                switch step {
                default:
                    Color.red
                }
            } else {
                // Config done
                Text("You are all set. Thanks") // Wait a few seconds, then leave the composition
            }
        }
        .transition(.slideForward)
    }
}

#Preview {
    AdditionalAccountDataView(additionalAccountData: [])
}
