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
    @ObservedObject private var viewModel: AdditionalAccountDataVM
    
    init(additionalAccountData: [AdditionalAccountData]) {
        self._viewModel = .init(wrappedValue: .init(additionalAccountData: additionalAccountData))
    }
    
    var body: some View {
        Group {
            if let step = viewModel.state.currentStep {
                Group {
                    switch step {
                    case .phoneNumber:
                        Color.yellow
                    case .business:
                        Color.black
                    case .nationality:
                        Color.gray
                    case .profilePicture:
                        Color.red
                    default:
                        EmptyView()
                    }
                }
                .transition(.slideForward)
                .overlay {
                    Text("\(step)")
                }
                .onTapGesture {
                    print("III")
                    viewModel.nextStep()
                }
            } else {
                // Config done
                Text("You are all set. Thanks") // Wait a few seconds, then leave the composition
            }
        }
        .animation(.smooth, value: viewModel.state.currentStep)
    }
}

#Preview {
    AdditionalAccountDataView(additionalAccountData: [])
}
