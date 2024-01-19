//
//  GlobalSettingsVM.swift
//  iosApp
//
//  Created by Jan Janovec on 12.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import shared
import SwiftUI

final class GlobalSettingsVM: GlobalSettingsViewModel, ObservableObject {
    @Published var state = GlobalSettingsContractState(appState: GlobalSettingsContractApplicationStateLoading())
    
    override init() {
        super.init()
        
        collect(flow: uiState as! SkieSwiftOptionalFlow<Any>) { _ in
            
        }
    }
}

extension GlobalSettingsVM {
    func finishOnboarding() {
        self.setEvent(event: GlobalSettingsContractEventFinishOnboarding())
    }
    
    func refreshAccount() {
        self.setEvent(event: GlobalSettingsContractEventRefreshAccount())
    }
}

struct TextFieldWrapper: View {
    @Binding var field: FormField
    @FocusState var isFocused
    var secureField: Bool = false
    var disabled: Bool = false
    
    var foregroundColor: Color {
        if disabled {
            return Color(resourceId: \.GRAY_300)
        }
        
        if field.validationResult is ValidationResultWarning || isFocused || !field.value.isEmpty {
            return Color(resourceId: \.GRAY_700)
        }
        
        if field.validationResult is ValidationResultError {
            return Color(resourceId: \.RED_500)
        }
        
        return Color(resourceId: \.GRAY_400)
    }
    
    var borderColor: Color {
        if disabled {
            return Color(resourceId: \.GRAY_300)
        }
        
        if field.validationResult is ValidationResultWarning || isFocused {
            return Color(resourceId: \.GRAY_700)
        }
        
        if field.validationResult is ValidationResultError {
            return Color(resourceId: \.RED_500)
        }
        
        return Color(resourceId: \.GRAY_200)
    }
    
    var body: some View {
        VStack(alignment: .leading, spacing: 6) {
            HStack(alignment: .center, spacing: 8) {
                if let image = field.icon, field.value.isEmpty {
                    Image(iconResourceId: image)
                }
                if secureField {
                    SecureField(field.placeHolder, text: $field.value)
                } else {
                    TextField(field.placeHolder, text: $field.value)
                }
            }
            .focused($isFocused)
            .foregroundStyle(foregroundColor)
            .padding(12)
            .background(disabled ? Color(resourceId: \.ZINC_50): Color.white)
            .overlay {
                RoundedRectangle(cornerRadius: 6)
                    .inset(by: 0.5)
                    .stroke(borderColor)
            }
            Text(field.description)
        }
    }
}
