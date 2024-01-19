//
//  OnboardingVM.swift
//  iosApp
//
//  Created by Jan Janovec on 16.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import shared
import SwiftUI

final class OnboardingVM: OnboardingViewModel, ObservableObject {
    @Published var state: OnboardingContractState = .init(onboardingStep: .welcome)
}

extension OnboardingVM {
    func skip() {
        self.setEvent(event: OnboardingContractEventSetOnboardingStep(step: OnboardingContractOnboardingStep.companion.items.last ?? .readyToSocialize))
    }
    
    func next() {
        self.setEvent(event: OnboardingContractEventNextStep())
    }
}
