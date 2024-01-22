//
//  AdditionalAccountDataVM.swift
//  iosApp
//
//  Created by Jan Janovec on 20.01.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import shared
import SwiftUI

final class AdditionalAccountDataVM: AdditionalAccountDataViewModel, ObservableObject {
    @Published var state: AdditionalAccountDataContractState = .init(stack: [], currentStep: nil)
    
    override init(additionalAccountData: [AdditionalAccountData]) {
        super.init(additionalAccountData: additionalAccountData)
        
        collect(flow: self.uiState, collect: { state in
            self.state = state as! AdditionalAccountDataContractState // swiftlint:disable:this force_cast
        })
    }
}

extension AdditionalAccountDataVM {
    func nextStep() {
        self.setEvent(event: AdditionalAccountDataContractEventNextStep())
    }
}
